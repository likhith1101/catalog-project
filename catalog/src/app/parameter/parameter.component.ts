import { Component } from '@angular/core';
import { Parameter } from '../Parameter';
import { AuthService } from '../auth.service';
import { ParameterService } from '../parameter.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-parameter',
  templateUrl: './parameter.component.html',
  styleUrls: ['./parameter.component.css']
})
export class ParameterComponent {
  data: Parameter[]=[];
  parameterForm: FormGroup;

  constructor(public authService: AuthService, private parameterService: ParameterService, private formBuilder: FormBuilder) {
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

  private loadData() {
    this.parameterService.getData().subscribe(res => this.data = res);
  }

  addParameter() {
    const newParameter: Parameter = this.parameterForm.value as Parameter;
    // Now, `newProduct` contains the values from the form controls.
    // You can call your service to add the product.
    this.parameterService.addParameter(newParameter).subscribe(res => alert('parameter added'));
  }
}
