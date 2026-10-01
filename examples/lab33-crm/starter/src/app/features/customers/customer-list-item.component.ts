import { Component, input, output } from '@angular/core';
import { Customer } from './customer.model';

@Component({
  selector: 'app-customer-list-item',
  standalone: true,
  template: `
    <!-- TODO: show customer().id, name, status; emit select on click -->
    <div class="customer-item">
      <div>
        <strong>{{ customer.name }}</strong>
        <div>{{ customer.id }}</div>
        <div>{{ customer.status }}</div>
      </div>

      <button type="button" (click)="select.emit(customer.id)">
        Select
      </button>
    </div>
  \`
    <button type="button" class="row">TODO list item</button>
  `,
  styles: `
    .row {
      display: block;
      width: 100%;
      text-align: left;
      margin: 0.25rem 0;
      padding: 0.5rem 0.75rem;
      cursor: pointer;
    }
  `,
})
export class CustomerListItemComponent {
  customer = input.required<Customer>();
  select = output<string>();
}
