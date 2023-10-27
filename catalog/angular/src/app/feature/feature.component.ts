import { Component, OnInit } from '@angular/core';
import { Feature } from '../Feature';
import { FeatureService } from '../feature.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Parameter } from '../Parameter';

@Component({
  selector: 'app-feature',
  templateUrl: './feature.component.html',
  styleUrls: ['./feature.component.css']
})
export class FeatureComponent implements OnInit {
  data: Feature[] = [];
  parameters: Parameter[] = [];
  showAddForm = false;
  showAddParameterForm = false;
  showEditForm = false;
  showLoadParameterForm = false;
  featureForm: FormGroup;
  parameterForm: FormGroup;
  editedFeature: Feature | null = null;
  selectedFeatureId: number | null = null;
  selectedFeatureParameters: Parameter[] = [];

  parameterTypes: string[] = ['QUANTITY','PRICE','OTHER'];

  constructor(
    private fb: FormBuilder,
    private featureService: FeatureService
  ) {
    this.featureForm = this.fb.group({
      name: [''],
      internalName: [''],
      details: [''],
    });
    this.parameterForm = this.fb.group({
      name: [''],
      internalName: [''],
      details: [''],
      parameterType: [''],
      values: ['']
    });
  }

  ngOnInit(): void {
    this.loadData();
  }

  toggleAddForm() {
    this.showAddForm = true;
  }

  toggleAddParameterForm(featureId: number) {
    this.showAddParameterForm = true;
    this.selectedFeatureId = featureId;
  }

  toggleLoadParameterForm(featureId: number) {
    this.showLoadParameterForm = true;
    this.selectedFeatureId = featureId;
    this.loadParametersByFeatureId();
  }

  toggleEditForm(feature: Feature) {
    this.showEditForm = true;
    this.editedFeature = feature;
  }

  loadData() {
    this.featureService.getData().subscribe((res) => {
      this.data = res;
    });
  }

  addFeature() {
    const newFeature: Feature = this.featureForm.value as Feature;
    this.featureService.addFeature(newFeature).subscribe(() => {
      this.loadData();
      console.log('Feature added');
    });
    this.featureForm.reset();
    this.showAddForm = false;
  }

  editFeature(featureId: number | undefined, updatedFeature: Feature) {
    if (featureId === undefined) {
      console.log('Please select a valid Feature ID.');
    } else {
      this.featureService.editFeature(featureId, updatedFeature).subscribe(() => {
        this.loadData();
        console.log('Feature updated successfully');
      });
      this.featureForm.reset();
      this.showEditForm = false;
    }
  }

  addParameterToFeature() {
    if (this.selectedFeatureId === null) {
      console.log('Please select a valid Feature ID.');
    } else {
      const newParameter: Parameter = this.parameterForm.value as Parameter;
      if (!newParameter) {
        console.log('Invalid parameter data.');
        return;
      }
      this.featureService.addParameterToFeature(this.selectedFeatureId, newParameter).subscribe(() => {
        this.loadData();
        console.log('Parameter added');
      });
      this.parameterForm.reset();
      this.showAddParameterForm = false;
    }
  }

  loadParametersByFeatureId() {
    if (this.selectedFeatureId === null) {
      console.log('Please select a valid Feature ID.');
      return; // Exit the function early if no valid ID is selected
    }

    this.featureService.getParametersByFeatureId(this.selectedFeatureId).subscribe(res => {
      this.selectedFeatureParameters = res; // Update selectedProductFeatures
    });
  }

  closeEditForm() {
    this.showEditForm = false;
    this.editedFeature = null;
  }

  closeAddParameterForm() {
    this.showAddParameterForm = false;
  }

  closeLoadParameterForm() {
    this.showLoadParameterForm = false;
  }
}
