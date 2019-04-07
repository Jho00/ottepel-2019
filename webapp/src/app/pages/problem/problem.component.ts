import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProblemsService} from "../../services/problems.service";
import {Problem} from "../../models/problem.model";
import LocationPicker from "location-picker";
import {UserService} from "../../services/user.service";
import {User} from "../../models/user.model";
import {Comment} from "../../models/comment.model";
import {CommentsService} from "../../services/comments.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
    selector: 'app-problem',
    templateUrl: './problem.component.html',
    styleUrls: ['./problem.component.scss']
})
export class ProblemComponent implements OnInit {
    public problem: Problem;
    public images: String[] = [];
    public lp: LocationPicker;
    private problemId: string;
    public scaled = false;
    public currentUser: User;
    public comments: Comment[] = [];
    public commentForm: FormGroup;
    public approved: boolean;
    public resolved: boolean;
    constructor(private route: ActivatedRoute,
                private problemsService: ProblemsService,
                private userService: UserService,
                private commentsService: CommentsService) {
    }

    ngOnInit() {
        this.commentForm = new FormGroup({
            comment: new FormControl('', Validators.required)
        });
        this.route.paramMap.subscribe(params => {
            this.problemId = params.get('id');
            this.checkForApprove();
            this.checkForResolve();
            this.problemsService.getProblemById(this.problemId).subscribe((data: any) => {
                this.problem = data.body.problem;
                this.images = data.body.images;
                this.userService.currentUserChange.subscribe(user => this.currentUser = user);
                this.lp = new LocationPicker('map');
                this.getComments();
                this.lp.setLocation(this.problem.lat, this.problem.lon);
            })
        })
    }

    public toggleScale() {
        this.scaled = !this.scaled;
    }

    public addComment(): void {
        this.commentsService.addComment(this.commentForm.controls['comment'].value, this.problem.id)
            .subscribe((data: any) => {
                this.commentForm.reset();
                this.comments = data.body;
            });
    }

    public approve(): void {
        if(localStorage.getItem('approves') === null) {
            let approves = [this.problemId];
            this.problemsService.approveProblem(this.problem.id).subscribe(() => {
                localStorage.setItem('approves', JSON.stringify(approves));
                this.approved = true;
            });

        } else {
            let approves = JSON.parse(localStorage.getItem('approves'));
            if (approves.indexOf(this.problemId) === -1) {
                this.problemsService.approveProblem(this.problem.id).subscribe(() => {
                    approves.push(this.problemId);
                    localStorage.setItem('approves', JSON.stringify(approves));
                    this.approved = true;
                });
            }

        }
    }

    private checkForResolve() {
        if(localStorage.getItem('resolves') !== null) {
            let resolves = JSON.parse(localStorage.getItem('resolves'));
            this.resolved = resolves.indexOf(this.problemId) !== -1;
        } else {
            this.resolved = false;
        }
    }

    public resolve() {
        if(localStorage.getItem('resolves') === null) {
            let resolves = [this.problemId];
            this.problemsService.resolveProblem(this.problem.id).subscribe(() => {
                localStorage.setItem('resolves', JSON.stringify(resolves));
                this.resolved = true;
            });

        } else {
            let resolves = JSON.parse(localStorage.getItem('resolves'));
            if (resolves.indexOf(this.problemId) === -1) {
                this.problemsService.resolveProblem(this.problem.id).subscribe(() => {
                    resolves.push(this.problemId);
                    localStorage.setItem('resolves', JSON.stringify(resolves));
                    this.resolved = true;
                });
            }

        }
    }

    private checkForApprove() {
        if(localStorage.getItem('approves') !== null) {
            let approves = JSON.parse(localStorage.getItem('approves'));
            this.approved = approves.indexOf(this.problemId) !== -1;
        } else {
            this.approved = false;
        }
    }

    private getComments(): void {
        this.commentsService.getComments(this.problem.id).subscribe((data: any) => this.comments = data.body);
    }

}
