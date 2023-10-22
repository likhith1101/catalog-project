import { Component } from '@angular/core';
import { Feature } from '../Feature';
import { AuthService } from '../auth.service';
import { FeatureService } from '../feature.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-feature',
  templateUrl: './feature.component.html',
  styleUrls: ['./feature.component.css']
})
export class FeatureComponent {
  data: Feature[]=[];
  featureForm: FormGroup;

  constructor(public authService: AuthService, private featureService: FeatureService, private formBuilder: FormBuilder) {
    this.featureForm = this.formBuilder.group({
      name: ['', Validators.required],
      internalName: ['', Validators.required],
      details: ['', Validators.required]
   })
  }

  ngOnInit() {
    this.loadData();
  }

  private loadData() {
    this.featureService.getData().subscribe(res => this.data = res);
  }

  addFeature() {
    const newFeature: Feature = this.featureForm.value as Feature;
    // Now, `newProduct` contains the values from the form controls.
    // You can call your service to add the product.
    this.featureService.addFeature(newFeature).subscribe(res => alert('feature added'));
  }

}
