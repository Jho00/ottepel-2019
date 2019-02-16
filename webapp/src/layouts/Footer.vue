<template>
    <footer class="footer">
        Footer
    </footer>
</template>

<script>
    export default {
        name: "Footer"
    }
</script>

<style lang="less">
    @import "../assets/styles/_varibales";
    footer {
        font-size: @font-size-medium;
        width: 100%;
        position: absolute;
        bottom: 0;
        left: 0;
        height: 50px;
    }
</style>