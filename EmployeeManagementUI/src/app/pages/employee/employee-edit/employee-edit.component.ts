import { CommonModule } from '@angular/common';
import { Component, effect, input, signal } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { EMPLOYEE_STATUS, GENDERS } from '../../../model/constants.model';
import { DepartmentService } from '../../../services/department-service';
import { EmployeeService } from '../../../services/employee-service';
@Component({
  selector: 'app-employee-edit',
  standalone: true,
  imports: [CommonModule, WidgetsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './employee-edit.component.html',
  styles: ``,
})
export class EmployeeEditComponent {
  edit = signal<boolean>(false);
  code = input<string>();

  departments = signal<any[]>([]);
  genders = signal<string[]>(GENDERS);
  statusList = signal<string[]>(EMPLOYEE_STATUS);

  form: FormGroup;

  constructor(
    builder: FormBuilder,
    departmentService: DepartmentService,
    private service: EmployeeService,
    private router: Router
  ) {
    this.form = builder.group({
      name: ['', Validators.required],
      department: ['', Validators.required],
      phone: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      gender: ['', Validators.required],
      dob: ['', Validators.required],
      status: ['', Validators.required],
      assignDate: ['', Validators.required],
      remark: [''],
    });

    departmentService.search({}).subscribe((result) => {
      if (result.success) {
        this.departments.set(result.payload);
      }
    });

    effect(() => {
      const id = this.code();

      if (id) {
        service.findById(id).subscribe((result) => {
          if (result.success) {
            this.edit.set(true);

            this.form.removeControl('department');
            this.form.removeControl('status');

            this.form.patchValue(result.payload);
          }
        });
      }
    });
  }

  save() {
    if (this.form.valid) {
      const action = this.edit()
        ? this.service.updateInfo(this.code()!, this.form.value)
        : this.service.create(this.form.value);

      action.subscribe((result) => {
        if (result.success) {
          this.router.navigate(['/employee', 'details'], {
            queryParams: { code: result.payload.id },
          });
        }
      });
    }
  }
}
