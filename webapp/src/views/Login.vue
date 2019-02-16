<template>
    <div class="wrapper">
        <el-row>
            <el-col :span="24">
                <el-form ref="form" :rules="rules" :model="form" label-width="25%">
                    <el-form-item label="Логин" prop="name">
                        <el-input v-model="form.name" ></el-input>
                    </el-form-item>
                    <el-form-item label="Пароль" prop="password">
                        <el-input type="password" v-model="form.password" ></el-input>
                    </el-form-item>
                    <el-form-item label="Чужой компьютер">
                        <el-switch v-model="form.alienComputer" ></el-switch>
                    </el-form-item>
                    <el-form-item class="pull-right">
                        <el-button type="primary" @click="submit('form')">Войти</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>

    </div>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                form: {
                    name: '',
                    password: '',
                    alienComputer: false
                },
                rules: {
                    name: [
                        { required: true, message: 'Пожалуйста, заполните поле', trigger: 'blur' },
                        { min: 3, message: 'Длина должна быть более 3 символов', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: 'Пожалуйста, заполните поле', trigger: 'blur' },
                        { min: 3, message: 'Длина должна быть более 3 символов', trigger: 'blur' }
                    ],
                }
            }
        },
        methods: {
            submit(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log('submit'); // TODO: change to send request for server-side
                    } else {
                        return false;
                    }
                });
            },
        }
    }
</script>

<style scoped>
.wrapper {
    width: 50% ;
    margin: 100px auto;
}
.pull-right {
    float: right;
    margin-right: 20px;
}
@media screen and (max-width: 480px) {
    .wrapper {
        width: 100% ;
    }
}
</style>