import { Component, OnInit } from '@angular/core';

export interface MPCoverage {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-mp-coverage-picker',
  templateUrl: './mp-coverage-picker.component.html',
  styleUrls: ['./mp-coverage-picker.component.css']
})
export class MpCoveragePickerComponent implements OnInit {
  mp: MPCoverage[] = [
    {value: 'true-0', viewValue: 'Coverage'},
    {value: 'false-1', viewValue: 'No Coverage'}
  ];
  constructor() { }

  ngOnInit() {
  }

}
