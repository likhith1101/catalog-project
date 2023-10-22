import { Component } from '@angular/core';
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
export class SummaryComponent {
  products: Product[] = [];
  features: Feature[] = [];
  parameters: Parameter[] = [];

  toggleNode(node: any) {
    node.expanded = !node.expanded;
  }

  constructor(private productService: ProductService, private featureService: FeatureService, private parameterService: ParameterService) { }

  ngOnInit(): void {
    // Fetch products, features, and edited products from your services
    this.productService.getData().subscribe((res) => {
      this.products = res;
    });

    


    this.featureService.getData().subscribe((res) => {
      this.features = res;
    });

    this.parameterService.getData().subscribe((res) => {
      this.parameters = res;
    });

    // For edited products, you may want to subscribe to events or data changes from your ProductComponent.
   
  }
}
