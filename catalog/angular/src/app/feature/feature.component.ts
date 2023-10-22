import { Component } from '@angular/core';
import { Feature } from '../Feature';
import { AuthService } from '../auth.service';
import { FeatureService } from '../feature.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ParameterService } from '../parameter.service';
import { Parameter } from '../Parameter';

@Component({
  selector: 'app-feature',
  templateUrl: './feature.component.html',
  styleUrls: ['./feature.component.css']
})
export class FeatureComponent {
  data: Feature[]=[];
  featureForm: FormGroup;
  parameterForm: FormGroup;
  showAddForm: boolean = false;
  showEditForm: boolean = false;
  showAddParameterForm: boolean = false;
  selectedFeatureId: number | undefined;
  parameterTypes: string[] = ['QUANTITY','PRICE','OTHER'];

  constructor(public authService: AuthService, private featureService: FeatureService, private parameterService: ParameterService, private formBuilder: FormBuilder) {
    this.featureForm = this.formBuilder.group({
      name: ['', Validators.required],
      internalName: ['', Validators.required],
      details: ['', Validators.required]
   })
   this.parameterForm = this.formBuilder.group({
    name: ['', Validators.required],
    internalName: ['', Validators.required],
    details: ['', Validators.required],
    parameterType: ['', Validators.required]
 })
  }

  ngOnInit() {
    this.loadData();
  }

  

  toggleEditForm() {
    this.showEditForm = !this.showEditForm;
  }

  toggleAddForm() {
    this.showAddForm = !this.showAddForm;
  }

  toggleAddParameterForm() {
    this.showAddParameterForm = !this.showAddParameterForm;
  }


  private loadData() {
    this.featureService.getData().subscribe(res => this.data = res);
  }

  addFeature() {
    const newFeature: Feature = this.featureForm.value as Feature;
    // Now, `newProduct` contains the values from the form controls.
    // You can call your service to add the product.
    this.featureService.addFeature(newFeature).subscribe(res => alert('feature added'));
    this.featureForm.reset();
    this.showAddForm = false;
  }

  editFeature() {
    if (this.selectedFeatureId === undefined) {
      alert('Please select a valid Feature ID.');
    } else {
      const updatedFeature: Feature = this.featureForm.value as Feature;
      this.featureService.editFeature(this.selectedFeatureId, updatedFeature).subscribe(() => {
        this.loadData(); // Refresh the product list after editing
        alert('Feature updated successfully');
      });
      this.featureForm.reset();
      this.showEditForm = false;
    }
  }

 



  addParameterToFeature() {
    if (this.selectedFeatureId === undefined) {
      alert('Please select a valid Parameter ID.');
    }
    else{
    const newParameter: Parameter = this.parameterForm.value as Parameter;
    this.featureService.addParameterToFeature(this.selectedFeatureId,newParameter).subscribe(() => {
      this.loadData(); // Refresh the product list after adding
      alert('Parameter added');
    });
    this.parameterForm.reset();
    this.showAddParameterForm = false;
  }
}

}
