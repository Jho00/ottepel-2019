import {Component, OnInit} from '@angular/core';
import LocationPicker from "location-picker";


@Component({
    selector: 'app-add-problem',
    templateUrl: './add-problem.component.html',
    styleUrls: ['./add-problem.component.scss']
})
export class AddProblemComponent implements OnInit {
    public lp: LocationPicker;

    constructor() {
    }

    ngOnInit() {
        this.lp = new LocationPicker('map');
        console.log(this.lp.getMarkerPosition());
    }

    public onFileChanged(event) {
        const file = event.target.files[0];
    }


}
