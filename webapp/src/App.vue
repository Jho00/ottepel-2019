<template>
    <div>
        <Header/>
        <main>
            <router-view/>
        </main>
        <!--<Footer/>-->
    </div>
</template>

<script>
    import Header from '@/layouts/Header.vue'
    import Footer from '@/layouts/Footer.vue'
    import Main from '@/layouts/Main.vue'

    export default {
        components: {
            Header,
            Footer,
            Main
        }
    }
</script>
<style lang="less">
    @import "assets/styles/_varibales";

    * {
        font-family: @global-font;
    }
</style>
