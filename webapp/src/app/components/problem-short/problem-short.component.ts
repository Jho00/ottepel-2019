import {Component, Input, OnInit} from '@angular/core';
import {Problem} from "../../models/problem.model";

@Component({
    selector: 'app-problem-short',
    templateUrl: './problem-short.component.html',
    styleUrls: ['./problem-short.component.scss']
})
export class ProblemShortComponent {
    @Input() public problem: Problem;
    @Input() public images: String[];

    toLocaleDateString(timestamp:string): string {
      return new Date(Date.parse(timestamp)).toLocaleDateString();
    }

}
