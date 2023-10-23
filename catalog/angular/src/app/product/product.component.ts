import { Component, Input, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { FeatureService } from '../feature.service';
import { Product } from '../Product';
import { AuthService } from '../auth.service'; // Correct the import path
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Feature } from '../Feature';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  data: Product[] = [];
  features: Feature[] = [];
  editMode = false;
  errorMessage: string | undefined;
  productForm: FormGroup;
  featureForm: FormGroup;
  selectedProductId: number | undefined;
  showAddForm: boolean = false;
  showEditForm: boolean = false;
  showDeleteForm: boolean = false;
  showAddFeatureForm: boolean =false;
  product: any;

  selectedProductFeatures: Feature[] = [];



  constructor(public authService: AuthService, private productService: ProductService, private featureService: FeatureService, private formBuilder: FormBuilder) {
    this.productForm = this.formBuilder.group({
      name: ['', Validators.required],
      internalName: ['', Validators.required],
      details: ['', Validators.required],
      maxProductsPerLocation: ['', Validators.required]
    });

    this.featureForm = this.formBuilder.group({
      name: ['', Validators.required],
      internalName: ['', Validators.required],
      details: ['', Validators.required],
    });

  }

  ngOnInit() {
    this.loadData();
  }

  private loadData() {
    this.productService.getData().subscribe(res => (this.data = res));
  }





  

   

  // private loadFeatures() {
  //   this.productService.getFeaturesByProductId(this.selectedProductId).subscribe(res => (this.features = res));
  // }


 

  // loadFeaturesByProductId() {
  //   console.log("I am called");
  //   if (this.selectedProductId === undefined) {
  //     alert('Please select a valid Feature ID.');
  //   }
  //   else{
  //     console.log("i ama perfect human");
  //   this.productService.getFeaturesByProductId(this.selectedProductId).subscribe(res => (this.features = res));
  //   // this.featureForm.reset();
  //   // this.showAddFeatureForm = false;
  // }
  // }
  

  
  


  toggleEditForm() {
    this.showEditForm = !this.showEditForm;
  }

  toggleAddForm() {
    this.showAddForm = !this.showAddForm;
  }

  toggleAddFeatureForm() {
    this.showAddFeatureForm = !this.showAddFeatureForm;
  }

  toggleDeleteForm() {
    this.showDeleteForm = !this.showDeleteForm;
  }

  addProduct() {
    const newProduct: Product = this.productForm.value as Product;
    this.productService.addProduct(newProduct).subscribe(() => {
      this.loadData(); // Refresh the product list after adding
      alert('Product added');
    });
    this.productForm.reset();
    this.showAddForm = false;
  }

  editProduct() {
    if (this.selectedProductId === undefined) {
      alert('Please select a valid Product ID.');
    } else {
      const updatedProduct: Product = this.productForm.value as Product;
      this.productService.editProduct(this.selectedProductId, updatedProduct).subscribe(() => {
        this.loadData(); // Refresh the product list after editing
        alert('Product updated successfully');
      });
      this.productForm.reset();
      this.showEditForm = false;
    }
  }

  loadFeaturesByProductId() {
    if (this.selectedProductId === undefined) {
      alert('Please select a valid Product ID.');
    } else {
      this.productService.getFeaturesByProductId(this.selectedProductId).subscribe(res => {
        this.features = res;
        this.selectedProductFeatures = res; // Update selectedProductFeatures
      });
    }
  }





  addFeatureToProduct() {
    if (this.selectedProductId === undefined) {
      alert('Please select a valid Feature ID.');
    }
    else{
    const newFeature: Feature = this.featureForm.value as Feature;
    
    this.productService.addFeatureToProduct(this.selectedProductId,newFeature).subscribe(() => {
      this.loadData(); // Refresh the product list after adding
      
      alert('Feature added');
    });
    this.featureForm.reset();
    this.showAddFeatureForm = false;
  }

}


}




