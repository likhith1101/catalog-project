import { Component } from '@angular/core';
import { Parameter } from '../Parameter';
import { AuthService } from '../services/auth.service';
import { ParameterService } from '../services/parameter.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FeatureService } from '../services/feature.service';
import { Feature } from '../Feature';

@Component({
  selector: 'app-parameter',
  templateUrl: './parameter.component.html',
  styleUrls: ['./parameter.component.css']
})
export class ParameterComponent {
  data: Parameter[]=[];
  features: Feature[]=[];
  parameterForm: FormGroup;
  featureForm: FormGroup;
  parameterTypes: string[] = ['QUANTITY','PRICE','OTHER'];
  selectedParameterId: number | undefined;
  selectedFeatureId: number | undefined;
  showAddForm: boolean = false;
  showEditForm: boolean = false;
  selectedFeatureParameters: Parameter[] = [];
  editedParameter: Parameter | null = null;
  editFormVisible: boolean[] = [];

  toggleEditForm(parameter: Parameter, index : number) {
    this.editFormVisible[index] = !this.editFormVisible[index];
    this.showEditForm = true;
    this.editedParameter = parameter;
  }

  toggleAddForm() {
    this.showAddForm = !this.showAddForm;
  }

  closeEditForm() {
    this.showEditForm = false;
    this.editedParameter = null;
  }


  constructor(public authService: AuthService, private parameterService: ParameterService, private featureService: FeatureService, private formBuilder: FormBuilder) {
    this.parameterForm = this.formBuilder.group({
      name: ['', Validators.required],
      internalName: ['', Validators.required],
      details: ['', Validators.required],
      parameterType: ['', Validators.required],
      values: ['', Validators.required]
   })
   this.featureForm = this.formBuilder.group({
    name: ['', Validators.required],
    internalName: ['', Validators.required],
    details: ['', Validators.required]
   })
   this.data.forEach(() => {
    this.editFormVisible.push(false);
   })
   }

  ngOnInit() {
    this.loadData();
  }

  private loadData() {
    this.parameterService.getData().subscribe(res => this.data = res);
  }

  
  private loadFeatures() {
    this.featureService.getData().subscribe(res => this.features = res);
  }



  addParameter() {
    const newParameter: Parameter = this.parameterForm.value as Parameter;
    // Now, `newProduct` contains the values from the form controls.
    // You can call your service to add the product.
    this.parameterService.addParameter(newParameter).subscribe(res => alert('parameter added'));
    this.parameterForm.reset();
    this.showAddForm = false;
  }

  editParameter(parameterId: number | undefined, updatedParameter: Parameter, index: number) {
    if (parameterId === undefined) {
      console.log('Please select a valid Parameter ID.');
    } else {
      this.parameterService.editParameter(parameterId, updatedParameter).subscribe(() => {
        this.loadData();
        console.log('Parameter updated successfully');
      });
      this.parameterForm.reset();
      this.showEditForm = false;
    }
  }

  loadParametersByFeatureId() {
    if (this.selectedFeatureId === undefined) {
      alert('Please select a valid Feature ID.');
    } else {
      this.featureService.getParametersByFeatureId(this.selectedFeatureId).subscribe(res => {
        this.data = res;
        this.selectedFeatureParameters = res; // Update selectedProductFeatures
      });
    }
  }

  deleteParameter(parameterId: number) {
    this.parameterService.deleteParameter(parameterId).subscribe(
      (deletedParameter: Parameter) => {
        const index = this.data.findIndex(parameter => parameter.parameterId === deletedParameter.parameterId);
        this.loadData();
        if (index !== -1) {
          this.data.splice(index, 1);
        } else {
          console.error("Parameter not found in the list");
        }
      },
      (error) => {
        console.error("Error occurred while deleting the parameter:", error);
      }
    );
  }


}