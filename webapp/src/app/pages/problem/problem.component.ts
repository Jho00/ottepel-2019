import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProblemsService} from "../../services/problems.service";
import {Problem} from "../../models/problem.model";
import LocationPicker from "location-picker";
import {UserService} from "../../services/user.service";
import {User} from "../../models/user.model";

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
    constructor(private route: ActivatedRoute,
                private problemsService: ProblemsService,
                private userService: UserService) {
    }

    ngOnInit() {
        this.route.paramMap.subscribe(params => {
            this.problemId = params.get('id');
            this.problemsService.getProblemById(this.problemId).subscribe((data: any) => {
                this.problem = data.body.problem;
                this.images = data.body.images;
                this.userService.currentUserChange.subscribe(user => this.currentUser = user);
                this.lp = new LocationPicker('map');
                this.lp.setLocation(this.problem.lat, this.problem.lon);
            })
        })
    }

    public toggleScale() {
        this.scaled = !this.scaled;
    }

    private fetchComments() {
      return [
        {
          text: 'Lorem ipsum',
          username: 'Nastya',
          likes: 20
        },
        {
          text: 'Lorem ipsum',
          username: 'Nastya',
          likes: 30
        },
        {
          text: 'Lorem ipsum',
          username: 'Nastya',
          likes: 60
        }
      ]
    }

}
