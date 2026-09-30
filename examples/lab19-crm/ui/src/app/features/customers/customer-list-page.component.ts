import { Component } from '@angular/core';

@Component({
  selector: 'app-customer-list-page',
  standalone: true,
  template: `
    <section data-testid="customer-list">
      <h2>Customers</h2>

      <div data-testid="customer-row-CUS-1001">
        CUS-1001 Amina Khan
      </div>

      <div data-testid="customer-row-CUS-1002">
        CUS-1002 Ravi Singh
      </div>

      <input data-testid="customer-create-name">

      <button data-testid="customer-create-submit">
        Create
      </button>
    </section>
  `,
})
export class CustomerListPageComponent {}