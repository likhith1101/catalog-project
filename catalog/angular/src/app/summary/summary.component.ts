import { Component, OnInit } from '@angular/core';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Product } from '../Product';
import { Feature } from '../Feature';
import { Parameter } from '../Parameter';
import { ProductService } from '../product.service';
import { FeatureService } from '../feature.service';
import { ParameterService } from '../parameter.service';

@Component({
  selector: 'app-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.css']
})
export class SummaryComponent implements OnInit {
  products: Product[] = [];
  features: Feature[] = [];
  parameters: Parameter[] = [];
  selectedProductId: number | undefined;
  selectedFeatureId: number | undefined;
  selectedParameterId: number | undefined;
  showAddForm: boolean = false;
  showAddProductForm: boolean = false;
  showEditProductForm: boolean = false;
  showAddFeatureForm: boolean = false;
  showEditFeatureForm: boolean = false;
  showAddParameterForm: boolean = false;
  showEditParameterForm: boolean = false;
  productForm: FormGroup;
  featureForm: FormGroup;
  parameterForm: FormGroup;
  parameterTypes: string[] = ['QUANTITY','PRICE','OTHER'];

  constructor(
    private productService: ProductService,
    private featureService: FeatureService,
    private parameterService: ParameterService,
    private formBuilder: FormBuilder // Add formBuilder
  ) {
    this.productForm = this.formBuilder.group({
      name: ['', Validators.required],
      internalName: ['', Validators.required],
      details: ['', Validators.required],
      maxProductsPerLocation: ['', Validators.required]
    });

    this.featureForm = this.formBuilder.group({
      name: ['', Validators.required],
      internalName: ['', Validators.required],
      details: ['', Validators.required]
   })

   this.parameterForm = this.formBuilder.group({
    name: ['', Validators.required],
    internalName: ['', Validators.required],
    details: ['', Validators.required],
    parameterType: ['', Validators.required],
    values: ['', Validators.required]
 })

  }

  ngOnInit() {
    this.loadProducts();
  }

  loadProducts() {
    this.productService.getData().subscribe((products) => {
      this.products = products;
    });
  }

  loadFeatures() {
    this.featureService.getData().subscribe((features) => {
      this.features = features;
    });
  }

  loadParameters() {
    this.parameterService.getData().subscribe((parameters) => {
      this.parameters = parameters;
    });
  }

  toggleAddForm() {
    this.showAddForm = !this.showAddForm;
  }

  toggleAddProductForm() {
    this.showAddProductForm = !this.showAddProductForm;
  }

  toggleEditProductForm() {
    this.showEditProductForm = !this.showEditProductForm;
  }

  addProduct() {
    const newProduct: Product = this.productForm.value as Product;
    this.productService.addProduct(newProduct).subscribe(() => {
      this.loadProducts();
      this.showAddProductForm = false;
    });
  }

  editProduct() {
    if (this.selectedProductId !== undefined) {
      const updatedProduct: Product = this.productForm.value as Product;
      this.productService.editProduct(this.selectedProductId, updatedProduct).subscribe(() => {
        this.loadProducts();
        this.showEditProductForm = false;
      });
    }
  }

  toggleAddFeatureForm() {
    this.showAddFeatureForm = !this.showAddFeatureForm;
  }

  toggleEditFeatureForm() {
    this.showEditFeatureForm = !this.showEditFeatureForm;
  }

  addFeature() {
    if (this.selectedProductId !== undefined) {
      const newFeature: Feature = this.featureForm.value as Feature;
      this.productService.addFeatureToProduct(this.selectedProductId, newFeature).subscribe(() => {
        this.loadFeatures();
        this.showAddFeatureForm = false;
      });
    }
  }

  editFeature() {
    if (this.selectedFeatureId !== undefined) {
      const updatedFeature: Feature = this.featureForm.value as Feature;
      this.featureService.editFeature(this.selectedFeatureId, updatedFeature).subscribe(() => {
        this.loadFeatures();
        this.showEditFeatureForm = false;
      });
    }
  }

  toggleAddParameterForm() {
    this.showAddParameterForm = !this.showAddParameterForm;
  }

  toggleEditParameterForm() {
    this.showEditParameterForm = !this.showEditParameterForm;
  }

  addParameter() {
    if (this.selectedFeatureId !== undefined) {
      const newParameter: Parameter = this.parameterForm.value as Parameter;
      this.featureService.addParameterToFeature(this.selectedFeatureId, newParameter).subscribe(() => {
        this.loadParameters();
        this.showAddParameterForm = false;
      });
    }
  }

  editParameter() {
    if (this.selectedParameterId !== undefined) {
      const updatedParameter: Parameter = this.parameterForm.value as Parameter;
      this.parameterService.editParameter(this.selectedParameterId, updatedParameter).subscribe(() => {
        this.loadParameters();
        this.showEditParameterForm = false;
      });
    }
  }

  private loadData() {
    this.productService.getData().subscribe(res => (this.products = res));
    this.featureService.getData().subscribe(res => (this.features = res));
    this.parameterService.getData().subscribe(res => (this.parameters = res));
  }

  // Load features by product ID
  loadFeaturesByProductId() {
    if (this.selectedProductId === undefined) {
      console.log('Please select a valid Product ID.');
    } else {
      this.productService.getFeaturesByProductId(this.selectedProductId).subscribe(res => {
        this.features = res;
        this.selectedFeatureId = undefined; // Reset the selected feature
        this.selectedParameterId = undefined; // Reset the selected parameter
      });
    }
  }

  // Add a feature to a product
  addFeatureToProduct() {
    if (this.selectedProductId === undefined) {
      console.log('Please select a valid Product ID.');
    } else {
      const newFeature: Feature = this.featureForm.value as Feature;
      this.productService.addFeatureToProduct(this.selectedProductId, newFeature).subscribe(() => {
        this.loadFeaturesByProductId();
        console.log('Feature added');
      });
      this.featureForm.reset();
      this.showAddFeatureForm = false;
    }
  }

  // Load parameters by feature ID
  loadParametersByFeatureId() {
    if (this.selectedFeatureId === undefined) {
      console.log('Please select a valid Feature ID.');
    } else {
      this.featureService.getParametersByFeatureId(this.selectedFeatureId).subscribe(res => {
        this.parameters = res;
        this.selectedParameterId = undefined; // Reset the selected parameter
      });
    }
  }

  // Add a parameter to a feature
  addParameterToFeature() {
    if (this.selectedFeatureId === undefined) {
      console.log('Please select a valid Feature ID.');
    } else {
      const newParameter: Parameter = this.parameterForm.value as Parameter;
      this.featureService.addParameterToFeature(this.selectedFeatureId, newParameter).subscribe(() => {
        this.loadParametersByFeatureId();
        console.log('Parameter added');
      });
      this.parameterForm.reset();
      this.showAddParameterForm = false;
    }
  }
}


