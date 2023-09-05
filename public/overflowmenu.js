function overflowMenu(subtree = document) {
    document.querySelectorAll("[data-overflow-menu]").forEach(menuRoot => {
        const button = menuRoot.querySelector("[aria-haspopup]"),
        menu = menuRoot.querySelector("[role=menu]"),
        items = [...menu.querySelectorAll("[role=menuitem]")];
        const isOpen = () => !menu.hidden;
        items.forEach(item => item.setAttribute("tabindex", "-1"));

        function toggleMenu(open) {
            if (open) {
                menu.hidden = false;
                button.setAttribute("aria-expanded", "true");
                items[0].focus();
            } else {
                menu.hidden = true;
                button.setAttribute("aria-expanded", "false");
            }
        }

        toggleMenu(isOpen());
        button.addEventListener("click", () => toggleMenu(!isOpen()));
        menuRoot.addEventListener("blur", e => {
            toggleMenu(false);
        });

    });
}

addEventListener("htmx:load", e => overflowMenu(e.target));

