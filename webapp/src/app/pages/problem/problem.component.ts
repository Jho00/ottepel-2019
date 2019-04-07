import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProblemsService} from "../../services/problems.service";
import {Problem} from "../../models/problem.model";
import LocationPicker from "location-picker";

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
    constructor(private route: ActivatedRoute,
                private problemsService: ProblemsService) {
    }

    ngOnInit() {
        this.lp = new LocationPicker('map');
        this.route.paramMap.subscribe(params => {
            this.problemId = params.get('id');
            this.problemsService.getProblemById(this.problemId).subscribe((data: any) => {
                this.problem = data.body.problem;
                this.images = data.body.images;
                this.lp.setLocation(this.problem.lat, this.problem.lon);
            })
        })
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
