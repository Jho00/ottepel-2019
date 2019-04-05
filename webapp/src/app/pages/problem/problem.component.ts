import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProblemsService} from "../../services/problems.service";
import {Problem} from "../../models/problem.model";

@Component({
    selector: 'app-problem',
    templateUrl: './problem.component.html',
    styleUrls: ['./problem.component.scss']
})
export class ProblemComponent implements OnInit {
    public problem: Problem;
    private problemId: string;
    constructor(private route: ActivatedRoute,
                private problemsService: ProblemsService) {
    }

    ngOnInit() {
        this.route.paramMap.subscribe(params => {
            this.problemId = params.get('id');
            this.problemsService.getProblemById(this.problemId).subscribe(problem => {
                this.problem = problem;
            })
        })
    }

}
