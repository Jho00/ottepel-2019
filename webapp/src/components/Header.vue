<template>
    <header class="header">
        <div class="content">
            <el-menu :default-active="activeIndex"
                     active-text-color="#ffd04b"
                     mode="horizontal"
                     text-color="#ffffff"
                     background-color="#70a1ef">
                <el-menu-item index="1">Главная</el-menu-item>
                <el-menu-item index="2">Регистрация</el-menu-item>
                <el-menu-item index="3">Вход</el-menu-item>
            </el-menu>
        </div>
    </header>
</template>

<script>
    export default {
        name: "Header",
        data() {
            return {
                activeIndex: '1'
            }
        }
    }
</script>

<style lang="less">
    @import "../assets/styles/_varibales";

    .header {
        font-size: @font-size-medium;
        width: 100%;
        position: absolute;
        top: 0;
        left: 0;
        background-color: @background-color-main;
        height: 60px;
        display: flex;
        justify-content: center;
        border-bottom: solid 1px #e6e6e6;

        .content {
            width: 70%;

        }
    }
</style>