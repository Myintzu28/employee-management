import { Routes } from '@angular/router';
import { SigninComponent } from './pages/signin/signin.component';
import { EmployeeComponent } from './pages/employee/employee.component';
import { EmployeeEditComponent } from './pages/employee/employee-edit/employee-edit.component';
import { EmployeeDetailsComponent } from './pages/employee/employee-details/employee-details.component';
import { DepartmentComponent } from './pages/department/department.component';
import { DepartmentEditComponent } from './pages/department/department-edit/department-edit.component';
import { DepartmentDetailsComponent } from './pages/department/department-details/department-details.component';

export const routes: Routes = [
  { path: 'signin', component: SigninComponent },
  {
    path: 'employee',
    children: [
      { path: 'list', component: EmployeeComponent },
      { path: 'edit', component: EmployeeEditComponent },
      { path: 'details', component: EmployeeDetailsComponent },
      { path: '', redirectTo: '/employee/list', pathMatch: 'full' },
    ],
  },
  {
    path: 'department',
    children: [
      { path: 'list', component: DepartmentComponent },
      { path: 'edit', component: DepartmentEditComponent },
      { path: 'details', component: DepartmentDetailsComponent },
      { path: '', redirectTo: '/department/list', pathMatch: 'full' },
    ],
  },
  { path: '', redirectTo: '/signin', pathMatch: 'full' },
];
