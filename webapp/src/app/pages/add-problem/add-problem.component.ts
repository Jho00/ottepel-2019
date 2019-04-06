import {Component, OnInit} from '@angular/core';
import LocationPicker from "location-picker";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ProblemsService} from "../../services/problems.service";


@Component({
    selector: 'app-add-problem',
    templateUrl: './add-problem.component.html',
    styleUrls: ['./add-problem.component.scss']
})
export class AddProblemComponent implements OnInit {
    public lp: LocationPicker;
    public selectedFiles: File[];
    public problemForm: FormGroup;
    public isDataLoaded = false;
    public problemId: number;
    public uploadData = new FormData();


    constructor(private fb: FormBuilder,
                private problemsService: ProblemsService) {
    }

    ngOnInit() {
        this.lp = new LocationPicker('map');
        this.problemForm = this.fb.group({
            title: new FormControl('', [Validators.required]),
            text: new FormControl('', [Validators.required]),
            lat: new FormControl(''),
            lon: new FormControl(''),
        })
    }

    public onFileChanged(event) {
        this.selectedFiles = event.target.files;
        for (let i = 0; i < this.selectedFiles.length; i++) {
            this.uploadData.append('images[]', this.selectedFiles[i].name);
        }
    }

    public submitForm(): void {
        this.problemForm.controls['lat'].setValue(this.lp.getMarkerPosition().lat);
        this.problemForm.controls['lon'].setValue(this.lp.getMarkerPosition().lng);
        this.problemsService.sendProblem(this.problemForm.value).subscribe((data: any) => {
            this.problemId = data.body.id;
            this.isDataLoaded = true;
        });
    }

    public sendPhotos(): void {
        this.uploadData.append('problemId', '12');
        this.problemsService.sendPhotos(this.uploadData).subscribe();
    }
}
