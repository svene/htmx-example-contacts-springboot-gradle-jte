@import gg.jte.Content

@param Content content



<!doctype html>
<html lang="">
<head>
    <title>Contact App</title>
    <link rel="stylesheet" href="https://unpkg.com/missing.css@1.1.1/dist/missing.min.css">
    <script src="https://unpkg.com/htmx.org@1.9.4" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/alpinejs"></script>
    <script src="/overflowmenu.js"></script>
    <link rel="stylesheet" href="/site.css">
    <%--
		<script src="/static/js/htmx-1.8.0.js"></script>
		<script src="/static/js/_hyperscript-0.9.7.js"></script>
		<script src="/static/js/rsjs-menu.js" type="module"></script>
	--%>
    <script defer src="https://unpkg.com/alpinejs@3/dist/cdn.min.js"></script>
</head>
<body hx-boost="true">
<div class="counter" x-data="{ count: 0 }">
    <output x-text="count"></output>
    <button x-on:click="count++">Increment</button>
</div>
<main>
    <header>
        <h1>
            <all-caps>contacts.app</all-caps>
            <sub-title>A Demo Contacts Application</sub-title>
        </h1>
    </header>
    ${content}
</main>
</body>
</html>
