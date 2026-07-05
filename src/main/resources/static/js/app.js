document.addEventListener("DOMContentLoaded", () => {
  initTheme();
  initDeleteConfirmations();
  initFlashToasts();
  initTaskFormConfirm();
});

function initTheme() {
  const toggle = document.getElementById("themeToggle");
  if (!toggle) return;

  updateThemeIcon();

  toggle.addEventListener("click", () => {
    const isDark =
      document.documentElement.getAttribute("data-theme") === "dark";
    if (isDark) {
      document.documentElement.removeAttribute("data-theme");
      localStorage.setItem("todo-theme", "light");
    } else {
      document.documentElement.setAttribute("data-theme", "dark");
      localStorage.setItem("todo-theme", "dark");
    }
    updateThemeIcon();
  });
}

function updateThemeIcon() {
  const toggle = document.getElementById("themeToggle");
  if (!toggle) return;
  const isDark = document.documentElement.getAttribute("data-theme") === "dark";
  toggle.innerHTML = isDark
    ? '<i class="fa-solid fa-sun"></i>'
    : '<i class="fa-solid fa-moon"></i>';
}

function initDeleteConfirmations() {
  document.querySelectorAll(".delete-form").forEach((form) => {
    form.addEventListener("submit", (e) => {
      e.preventDefault();
      Swal.fire({
        title: "Delete this task?",
        text: "This action cannot be undone.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#dc3545",
        cancelButtonColor: "#6c757d",
        confirmButtonText: "Yes, delete it",
      }).then((result) => {
        if (result.isConfirmed) {
          form.submit();
        }
      });
    });
  });
}

function initFlashToasts() {
  const el = document.getElementById("flashToast");
  if (!el) return;

  const type = el.dataset.toast;
  const messages = {
    created: { icon: "success", title: "Task created successfully!" },
    updated: { icon: "success", title: "Task updated successfully!" },
    deleted: { icon: "success", title: "Task deleted successfully!" },
    completed: { icon: "success", title: "Task marked as complete!" },
    pending: { icon: "info", title: "Task marked as pending." },
  };

  const msg = messages[type];
  if (msg) {
    Swal.fire({
      toast: true,
      position: "top-end",
      icon: msg.icon,
      title: msg.title,
      showConfirmButton: false,
      timer: 3000,
      timerProgressBar: true,
    });
  }
}

function initTaskFormConfirm() {
  const form = document.getElementById("taskForm");
  if (!form) return;

  form.addEventListener("submit", (e) => {
    e.preventDefault();
    const isEdit =
      form.action.includes("/tasks/") && !form.action.endsWith("/tasks");
    Swal.fire({
      title: isEdit ? "Save changes?" : "Create this task?",
      icon: "question",
      showCancelButton: true,
      confirmButtonColor: "#4361ee",
      cancelButtonColor: "#6c757d",
      confirmButtonText: isEdit ? "Yes, save" : "Yes, create",
    }).then((result) => {
      if (result.isConfirmed) {
        form.submit();
      }
    });
  });
}
