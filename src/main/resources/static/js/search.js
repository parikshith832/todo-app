document.addEventListener('DOMContentLoaded', () => {
    const searchInput = document.getElementById('taskSearch');
    if (!searchInput) return;

    const taskItems = document.querySelectorAll('.task-item');
    const noResults = document.getElementById('noSearchResults');
    const taskList = document.getElementById('taskList');

    searchInput.addEventListener('input', () => {
        const query = searchInput.value.trim().toLowerCase();
        let visibleCount = 0;

        taskItems.forEach(item => {
            const title = item.dataset.title || '';
            const match = title.includes(query);
            item.classList.toggle('d-none', !match);
            if (match) visibleCount++;
        });

        if (taskList) {
            taskList.classList.toggle('d-none', visibleCount === 0 && query.length > 0);
        }
        if (noResults) {
            noResults.classList.toggle('d-none', visibleCount > 0 || query.length === 0);
        }
    });
});
