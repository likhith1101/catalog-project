import { Component, Input, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { Product } from '../Product';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Feature } from '../Feature';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  data: Product[] = [];
  features: Feature[] = [];
  showAddForm = false;
  showAddProductForm = false;
  showAddFeatureForm = false;
  showEditForm = false;
  showLoadFeatureForm = false;
  productForm: FormGroup;
  featureForm: FormGroup;
  editedProduct: Product | null = null;
  selectedProductId: number | null = null;
  selectedProductFeatures: Feature[] = [];

  constructor(private fb: FormBuilder, private http: HttpClient, private productService: ProductService) {
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
  }

  ngOnInit(): void {
    this.loadData();
  }

  toggleAddForm() {
    this.showAddForm = true;
  }

  toggleAddFeatureForm(productId: number) {
    this.showAddFeatureForm = true;
    this.selectedProductId = productId;
  }

  toggleLoadFeatureForm(productId: number) {
    this.showLoadFeatureForm = true;
    this.selectedProductId = productId;
    this.loadFeaturesByProductId();
  }

  toggleEditForm(product: Product) {
    this.showEditForm = true;
    this.editedProduct = product;
  }

  loadData() {
    this.productService.getData().subscribe((res) => (this.data = res));
  }

  addProduct() {
    const newProduct: Product = this.productForm.value as Product;
    this.productService.addProduct(newProduct).subscribe(() => {
      this.loadData();
      console.log('Product added');
    });
    this.productForm.reset();
    this.showAddForm = false;
  }

  editProduct(productId: number | undefined, updatedProduct: Product) {
    if (productId === undefined) {
      console.log('Please select a valid Product ID.');
    } else {
      this.productService.editProduct(productId, updatedProduct).subscribe(() => {
        this.loadData();
        console.log('Product updated successfully');
      });
      this.productForm.reset();
      this.showEditForm = false;
    }
  }

  addFeatureToProduct() {
    if (this.selectedProductId === null) {
      console.log('Please select a valid Product ID.');
    } else {
      const newFeature: Feature = this.featureForm.value as Feature;
      if (!newFeature) {
        console.log('Invalid feature data.');
        return;
      }
      this.productService.addFeatureToProduct(this.selectedProductId, newFeature).subscribe(() => {
        this.loadData();
        console.log('Feature added');
      });
      this.featureForm.reset();
      this.showAddFeatureForm = false;
    }
  }

  loadFeaturesByProductId() {
    if (this.selectedProductId === null) {
      console.log('Please select a valid Product ID.');
      return; // Exit the function early if no valid ID is selected
    }

    this.productService.getFeaturesByProductId(this.selectedProductId).subscribe(res => {
      this.selectedProductFeatures = res; // Update selectedProductFeatures
    });
  }

  closeAddProductForm() {
    this.showAddProductForm = false;
  }

  closeEditForm() {
    this.showEditForm = false;
    this.editedProduct = null;
  }

  closeAddFeatureForm() {
    this.showAddFeatureForm = false;
  }

  closeLoadFeatureForm() {
    this.showLoadFeatureForm = false;
  }
}
