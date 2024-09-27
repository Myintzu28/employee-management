import {
  Component,
  ViewChild,
  computed,
  effect,
  input,
  signal,
} from '@angular/core';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { RouterLink } from '@angular/router';
import { EmployeeService } from '../../../services/employee-service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-employee-details',
  standalone: true,
  imports: [WidgetsModule, RouterLink, CommonModule],
  templateUrl: './employee-details.component.html',
  styles: ``,
})
export class EmployeeDetailsComponent {
  code = input.required<string>();
  info = signal<any>(undefined);

  title = computed(() => `${this.info()?.code} - ${this.info()?.name}`);

  constructor(private service: EmployeeService) {
    effect(() => {
      this.reload();
    });
  }

  onChange(event: any) {
    if (event) {
      this.reload();
    }
  }

  private reload() {
    if (this.code()) {
      this.service.findById(this.code()).subscribe((result) => {
        if (result.success) {
          this.info.set(result.payload);
        }
      });
    }
  }
}
