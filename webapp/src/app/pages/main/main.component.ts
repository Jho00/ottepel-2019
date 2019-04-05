import {Component, OnDestroy, OnInit} from '@angular/core';
import {ProblemsService} from "../../services/problems.service";
import {Problem} from "../../models/problem.model";
import {Subscription} from "rxjs";

@Component({
    selector: 'app-main',
    templateUrl: './main.component.html',
    styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit, OnDestroy {
    public problemsList: Problem[] = [];
    private _subs: Subscription[] = [];

    constructor(private problemsService: ProblemsService) {
    }

    ngOnInit(): void {
        this._subs.push(this.problemsService.getProblemsList().subscribe(data => this.problemsList = data));
    }

    ngOnDestroy(): void {
        this._subs.forEach(sub => sub.unsubscribe());
    }
}
