// @ts-ignore
import { Component } from '@angular/core';
import { CustomerListPageComponent } from './features/customers/customer-list-page.component';

// @ts-ignore
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CustomerListPageComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {}
