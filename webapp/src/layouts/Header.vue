<template>
    <el-header>
        <header class="header">
            <div class="content">
                <el-menu :router="true" :default-active="activeIndex" mode="horizontal">
                    <el-menu-item route="/" index="1">Главная</el-menu-item>
                    <el-menu-item index="2">Регистрация</el-menu-item>
                    <el-menu-item route="/login" index="3">Вход</el-menu-item>
                </el-menu>
            </div>
        </header>
    </el-header>
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
        height: 60px;
        display: flex;
        justify-content: center;
        border-bottom: solid 1px #e6e6e6;

        .content {
            width: 100%;
            padding: 5px;
            box-sizing: border-box;
        }
    }
</style>