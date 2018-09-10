import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-form-stepper',
  templateUrl: './form-stepper.component.html',
  styleUrls: ['./form-stepper.component.css']
})
export class FormStepperComponent implements OnInit {
  personalFormGroup: FormGroup;
  schoolFormGroup: FormGroup;
  instrumentFormGroup: FormGroup;
  descriptionFormGroup: FormGroup;
  constructor(private _formBuilder: FormBuilder) { }

  ngOnInit() {
    this.personalFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.schoolFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
    this.instrumentFormGroup = this._formBuilder.group({
      thirdCtrl: ['', Validators.required]
    });
    this.descriptionFormGroup = this._formBuilder.group({
      fourthCtrl: ['', Validators.required]
    });
  }

}
