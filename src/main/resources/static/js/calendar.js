document.addEventListener('DOMContentLoaded', () => {
    const container = document.getElementById('calendar');
    if (!container) return;

    let currentYear = parseInt(container.dataset.year, 10);
    let currentMonth = parseInt(container.dataset.month, 10);
    let selectedDate = container.dataset.selected;

    const tasksByDate = window.calendarTasksByDate || {};

    renderCalendar();

    function renderCalendar() {
        const firstDay = new Date(currentYear, currentMonth - 1, 1);
        const lastDay = new Date(currentYear, currentMonth, 0);
        const startDayOfWeek = firstDay.getDay();
        const daysInMonth = lastDay.getDate();

        const monthNames = ['January', 'February', 'March', 'April', 'May', 'June',
            'July', 'August', 'September', 'October', 'November', 'December'];
        const dayNames = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];

        const today = new Date();
        const todayStr = formatDate(today.getFullYear(), today.getMonth() + 1, today.getDate());

        let html = `
            <div class="calendar-header">
                <button class="btn btn-sm btn-outline-primary" id="prevMonth">
                    <i class="fa-solid fa-chevron-left"></i>
                </button>
                <h4>${monthNames[currentMonth - 1]} ${currentYear}</h4>
                <button class="btn btn-sm btn-outline-primary" id="nextMonth">
                    <i class="fa-solid fa-chevron-right"></i>
                </button>
            </div>
            <div class="calendar-grid">
        `;

        dayNames.forEach(name => {
            html += `<div class="calendar-day-name">${name}</div>`;
        });

        for (let i = 0; i < startDayOfWeek; i++) {
            html += `<div class="calendar-day empty"></div>`;
        }

        for (let day = 1; day <= daysInMonth; day++) {
            const dateStr = formatDate(currentYear, currentMonth, day);
            const classes = ['calendar-day'];
            if (dateStr === todayStr) classes.push('today');
            if (dateStr === selectedDate) classes.push('selected');
            if (tasksByDate[dateStr]) classes.push('has-tasks');

            html += `<div class="${classes.join(' ')}" data-date="${dateStr}">${day}</div>`;
        }

        html += '</div>';
        container.innerHTML = html;

        document.getElementById('prevMonth').addEventListener('click', () => {
            navigateMonth(-1);
        });
        document.getElementById('nextMonth').addEventListener('click', () => {
            navigateMonth(1);
        });

        container.querySelectorAll('.calendar-day:not(.empty)').forEach(dayEl => {
            dayEl.addEventListener('click', () => {
                selectedDate = dayEl.dataset.date;
                navigateToDate(selectedDate);
            });
        });
    }

    function navigateMonth(delta) {
        currentMonth += delta;
        if (currentMonth > 12) {
            currentMonth = 1;
            currentYear++;
        } else if (currentMonth < 1) {
            currentMonth = 12;
            currentYear--;
        }
        renderCalendar();
    }

    function navigateToDate(dateStr) {
        window.location.href = `/calendar?year=${currentYear}&month=${currentMonth}&selectedDate=${dateStr}`;
    }

    function formatDate(year, month, day) {
        return `${year}-${String(month).padStart(2, '0')}-${String(day).padStart(2, '0')}`;
    }
});
