import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule, Routes} from '@angular/router';
import {HeaderComponent} from './components/header/header.component';
import {FooterComponent} from './components/footer/footer.component';
import {MainComponent} from './pages/main/main.component';
import {AddProblemComponent} from './pages/add-problem/add-problem.component';
import {ProblemShortComponent} from './components/problem-short/problem-short.component';
import {HttpClientModule} from "@angular/common/http";
import {ProblemComponent} from './pages/problem/problem.component';
import { LoginComponent } from './pages/login/login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

const appRoutes: Routes = [
    {
        path: 'main',
        component: MainComponent,
        pathMatch: 'full'
    },
    {
        path: 'add-problem',
        component: AddProblemComponent,
        pathMatch: 'full'
    },
    {
        path: '',
        redirectTo: '/main',
        pathMatch: 'full'
    },
    {
        path: 'problem/:id',
        component: ProblemComponent,
        pathMatch: 'full'
    },
    {
        path: 'login',
        component: LoginComponent,
        pathMatch: 'full'
    }
];

@NgModule({
    declarations: [
        AppComponent,
        HeaderComponent,
        FooterComponent,
        MainComponent,
        AddProblemComponent,
        ProblemShortComponent,
        ProblemComponent,
        LoginComponent
    ],
    imports: [
        RouterModule.forRoot(appRoutes),
        BrowserModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
