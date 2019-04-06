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
    public upload: any[] = [];


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
            this.readThis(this.selectedFiles[i]);
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
        this.problemsService.sendPhotos(JSON.stringify({images: this.upload}), 14).subscribe();
    }

    private readThis(inputValue: any): void {
        let file: File = inputValue;
        let myReader:FileReader = new FileReader();

        myReader.onloadend = (e) => {
            let image = myReader.result;
            this.upload.push(image);
        };
        myReader.readAsDataURL(file);
    }
}
