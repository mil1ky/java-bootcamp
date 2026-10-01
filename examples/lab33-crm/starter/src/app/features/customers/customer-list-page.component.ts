import { Component } from '@angular/core';
import { CustomerListItemComponent } from './customer-list-item.component';
import {Customer, SEED_CUSTOMERS} from './customer.model';

@Component({
  selector: 'app-customer-list-page',
  standalone: true,
  imports: [CustomerListItemComponent],
  template: `
    <section>
      <h2>Customers</h2>
      <!-- TODO: @for (c of customers; track c.id) { app-customer-list-item + (select) } -->
       @for (customer of customers; track customer.id) {
        <app-customer-list-item
          [customer]="customer"
          (select)="selectCustomer($event)">
        </app-customer-list-item>
      }

      @if (selectedId) {
        <p>
          Selected customer: <strong>{{ selectedId }}</strong>
        </p>
      }
    </section>
  \`
      <p class="detail">
        Selected: {{ selectedId ?? 'none' }}
      </p>
    </section>
  `,
})
export class CustomerListPageComponent {
  // TODO: assign SEED_CUSTOMERS
  customers: Customer[] = SEED_CUSTOMERS;
  selectedId: string | null = null;

  onSelect(id: string): void {
    this.selectedId = id;
  }
}
