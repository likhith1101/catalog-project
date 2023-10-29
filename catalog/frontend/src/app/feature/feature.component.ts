import { Component, OnInit } from '@angular/core';
import { Feature } from '../Feature';
import { FeatureService } from '../services/feature.service';
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
  editFormVisible: boolean[] = [];
  addParameterFormVisible: boolean[] = [];
  loadParameterFormVisible: boolean[] = [];
  productForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private featureService: FeatureService
  ) {
    this.productForm = this.fb.group({
      name: [''],
      internalName: [''],
      details: [''],
      maxProductsPerLocation: [0],
    });
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
    this.data.forEach(() => {
      this.editFormVisible.push(false);
      this.addParameterFormVisible.push(false); 
      this.loadParameterFormVisible.push(false);// Initialize all forms as hidden initially
    });
  }

  ngOnInit(): void {
    this.loadData();
  }

  toggleAddForm() {
    this.showAddForm = true;
  }

  toggleAddParameterForm(featureId: number, index : number) {
    this.addParameterFormVisible[index] = !this.addParameterFormVisible[index];
    this.showAddParameterForm = true;
    this.selectedFeatureId = featureId;
  }

  toggleLoadParameterForm(featureId: number, index : number) {
    this.loadParameterFormVisible[index] = !this.loadParameterFormVisible[index];
    this.showLoadParameterForm = true;
    this.selectedFeatureId = featureId;
    this.loadParametersByFeatureId();
  }

  toggleEditForm(feature: Feature, index : number) {
    this.editFormVisible[index] = !this.editFormVisible[index];
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

  editFeature(featureId: number | undefined, updatedFeature: Feature, index : number) {
    if (featureId === undefined) {
      console.log('Please select a valid Feature ID.');
    } else {
      this.featureService.editFeature(featureId, updatedFeature).subscribe(() => {
        this.loadData();
        this.editFormVisible[index] = false;
        console.log('Feature updated successfully');
      });
      this.featureForm.reset();
      this.showEditForm = false;
    }
  }

  addParameterToFeature(index: number) {
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
        this.addParameterFormVisible[index] = false;
        console.log('Parameter added');
      });
      this.parameterForm.reset();
      this.showAddParameterForm = false;
    }
  }

  deleteFeature(featureId: number) {
    this.featureService.deleteFeature(featureId).subscribe(
      (deletedFeature: Feature) => {
        const index = this.data.findIndex(feature => feature.featureId === deletedFeature.featureId);
        this.loadData();
        if (index !== -1) {
          this.data.splice(index, 1);
        } else {
          console.error("Feature not found in the list");
        }
      },
      (error) => {
        console.error("Error occurred while deleting the feature:", error);
      }
    );
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
