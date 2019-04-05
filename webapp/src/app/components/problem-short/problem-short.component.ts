import {Component, Input} from '@angular/core';
import {Problem} from "../../models/problem.model";

@Component({
    selector: 'app-problem-short',
    templateUrl: './problem-short.component.html',
    styleUrls: ['./problem-short.component.scss']
})
export class ProblemShortComponent {
    @Input() public problem: Problem;
}
