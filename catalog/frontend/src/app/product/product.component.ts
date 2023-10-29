import { Component, Input, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
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
  // ProductId: number | null = null;
  selectedProductFeatures: Feature[] = [];
  editFormVisible: boolean[] = [];
  addFeatureFormVisible: boolean[] = [];
  loadFeatureFormVisible: boolean[] = [];

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
    this.data.forEach(() => {
      this.editFormVisible.push(false);
      this.addFeatureFormVisible.push(false); 
      this.loadFeatureFormVisible.push(false);// Initialize all forms as hidden initially
    });
  }

  ngOnInit(): void {
    this.loadData();
    
  }

  toggleAddForm(): void {
    this.showAddForm = !this.showAddForm;
    if (this.showAddForm) {
      // If the form is shown, reset the form fields
      this.productForm.reset();
    }
  }
 
  toggleAddFeatureForm(productId: number, index: number) {
    this.addFeatureFormVisible[index] = !this.addFeatureFormVisible[index];
    this.showAddFeatureForm = true;
    this.selectedProductId = productId;
  }

  toggleLoadFeatureForm(productId: number, index: number) {
    this.loadFeatureFormVisible[index] = !this.loadFeatureFormVisible[index];
    this.showLoadFeatureForm = true;
    this.selectedProductId = productId;
    this.loadFeaturesByProductId(index);
  }

  toggleEditForm(product: Product, index:number) {
    this.editFormVisible[index] = !this.editFormVisible[index];
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
    this.showAddForm = false;
  }

  editProduct(productId: number | undefined, updatedProduct: Product,index:number) {
    if (productId === undefined) {
      console.log('Please select a valid Product ID.');
    } else {
      this.productService.editProduct(productId, updatedProduct).subscribe(() => {
        this.loadData(); 
         this.editFormVisible[index] = false;// Assuming this updates the 'data' array from an external source or service
        console.log('Product updated successfully');
      });
  
      // Reset the form and hide the form only after successful update
      this.productForm.reset();
      this.showEditForm = false;

    }
  }

  addFeatureToProduct(index: number) {
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
        this.addFeatureFormVisible[index] = false;
        console.log('Feature added');
      });
      this.featureForm.reset();
      this.showAddFeatureForm = false;
    }
  }

  loadFeaturesByProductId(index:number) {
    if (this.selectedProductId === null) {
      console.log('Please select a valid Product ID.');
      return; // Exit the function early if no valid ID is selected
    }

    this.productService.getFeaturesByProductId(this.selectedProductId).subscribe(res => {
      this.selectedProductFeatures = res; // Update selectedProductFeatures
    });
  }

  deleteProduct(productId: number) {
    this.productService.deleteProduct(productId).subscribe((response) => {
      if (response) {
        const index = this.data.findIndex(product => product.id === productId);
        if (index > -1) {
          this.data.splice(index, 1); // Reload the page after successful deletion
        }
      } else {
        console.error('Deletion failed:', response);
      }
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