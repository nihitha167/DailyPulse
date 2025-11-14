// Helper: format date as YYYY-MM-DD
function formatDate(date) {
  return date.toISOString().slice(0, 10);
}

// Compute last 7-day window (today and previous 6 days)
function getLast7DaysRange() {
  const today = new Date();
  const to = formatDate(today);
  const fromDate = new Date(today);
  fromDate.setDate(fromDate.getDate() - 6);
  const from = formatDate(fromDate);
  return { from, to };
}

// ---- API wrappers ----

async function fetchHabits() {
  const res = await fetch("/api/habits");
  if (!res.ok) throw new Error("Failed to load habits");
  return await res.json();
}

async function createHabit(name, description) {
  const res = await fetch("/api/habits", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ name, description })
  });
  if (!res.ok) {
    const err = await res.json().catch(() => null);
    throw new Error(err?.message || "Failed to create habit");
  }
  return await res.json();
}

async function logCheckin(payload) {
  const res = await fetch("/api/checkins", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload)
  });
  if (!res.ok) {
    const err = await res.json().catch(() => null);
    throw new Error(err?.message || "Failed to save check-in");
  }
  return await res.json();
}

async function fetchSummary(from, to) {
  const url = `/api/analytics/summary?from=${from}&to=${to}`;
  const res = await fetch(url);
  if (!res.ok) throw new Error("Failed to load summary");
  return await res.json();
}

// ---- UI rendering ----

async function renderHabits() {
  const habitList = document.getElementById("habit-list");
  const checkinHabits = document.getElementById("checkin-habits");
  habitList.innerHTML = "<li>Loading...</li>";
  checkinHabits.innerHTML = "<p>Loading habits...</p>";

  try {
    const habits = await fetchHabits();

    // List view
    if (habits.length === 0) {
      habitList.innerHTML = "<li>No habits yet. Add one above.</li>";
    } else {
      habitList.innerHTML = "";
      habits.forEach(h => {
        const li = document.createElement("li");
        li.textContent = h.description
          ? `${h.name} – ${h.description}`
          : h.name;
        habitList.appendChild(li);
      });
    }

    // Checkboxes
    checkinHabits.innerHTML = "";
    if (habits.length === 0) {
      const p = document.createElement("p");
      p.textContent = "Add habits first, then you can mark them as completed.";
      checkinHabits.appendChild(p);
    } else {
      habits.forEach(h => {
        const label = document.createElement("label");
        const checkbox = document.createElement("input");
        checkbox.type = "checkbox";
        checkbox.value = String(h.id);
        label.appendChild(checkbox);
        label.appendChild(document.createTextNode(h.name));
        checkinHabits.appendChild(label);
      });
    }
  } catch (err) {
    habitList.innerHTML = `<li class="error">Error: ${err.message}</li>`;
    checkinHabits.innerHTML = `<p class="error">Error loading habits.</p>`;
  }
}

async function renderSummary() {
  const summaryDiv = document.getElementById("summary");
  summaryDiv.innerHTML = "<p>Loading summary...</p>";
  const { from, to } = getLast7DaysRange();

  try {
    const summary = await fetchSummary(from, to);
    summaryDiv.innerHTML = `
      <p><strong>Range:</strong> ${summary.from} → ${summary.to}</p>
      <p><strong>Total check-ins:</strong> ${summary.totalCheckins}</p>
      <p><strong>Average mood:</strong> ${summary.averageMood.toFixed(2)}</p>
      <p><strong>Total completed habits:</strong> ${summary.totalCompletedHabits}</p>
    `;
  } catch (err) {
    summaryDiv.innerHTML = `<p class="error">Error: ${err.message}</p>`;
  }
}

// ---- Event wiring ----

document.addEventListener("DOMContentLoaded", () => {
  const habitForm = document.getElementById("habit-form");
  const habitNameInput = document.getElementById("habit-name");
  const habitDescInput = document.getElementById("habit-description");

  const checkinForm = document.getElementById("checkin-form");
  const checkinDateInput = document.getElementById("checkin-date");
  const checkinMoodSelect = document.getElementById("checkin-mood");
  const checkinNotesInput = document.getElementById("checkin-notes");
  const checkinHabitsDiv = document.getElementById("checkin-habits");

  // Default date to today
  checkinDateInput.value = formatDate(new Date());

  // Initial load
  renderHabits();
  renderSummary();

  // Handle habit form submit
  habitForm.addEventListener("submit", async (e) => {
    e.preventDefault();
    const name = habitNameInput.value.trim();
    const description = habitDescInput.value.trim();

    if (!name) {
      alert("Please enter a habit name.");
      return;
    }

    try {
      await createHabit(name, description);
      habitNameInput.value = "";
      habitDescInput.value = "";
      await renderHabits();
    } catch (err) {
      alert(err.message);
    }
  });

  // Handle check-in form submit
  checkinForm.addEventListener("submit", async (e) => {
    e.preventDefault();

    const date = checkinDateInput.value;
    const mood = parseInt(checkinMoodSelect.value, 10);
    const notes = checkinNotesInput.value.trim();

    if (!date || Number.isNaN(mood)) {
      alert("Please select a date and mood.");
      return;
    }

    // Collect completed habit IDs
    const checkboxes = checkinHabitsDiv.querySelectorAll("input[type='checkbox']");
    const completedHabitIds = [];
    checkboxes.forEach(cb => {
      if (cb.checked) {
        completedHabitIds.push(Number(cb.value));
      }
    });

    const payload = {
      date,
      mood,
      notes,
      completedHabitIds
    };

    try {
      await logCheckin(payload);
      // Optionally clear notes and uncheck
      checkinNotesInput.value = "";
      checkboxes.forEach(cb => cb.checked = false);
      await renderSummary();
      alert("Check-in saved!");
    } catch (err) {
      alert(err.message);
    }
  });
});
