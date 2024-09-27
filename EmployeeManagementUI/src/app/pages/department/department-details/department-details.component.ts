import { Component, computed, effect, input, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { WidgetsModule } from '../../../widgets/widgets.module';
import { DepartmentService } from '../../../services/department-service';
import { Information } from '../../../model/pager';

@Component({
  selector: 'app-department-details',
  standalone: true,
  imports: [CommonModule, WidgetsModule, RouterLink],
  templateUrl: './department-details.component.html',
  styles: ``,
})
export class DepartmentDetailsComponent {
  code = input.required<string>();

  informations = computed<Information[]>(() => {
    const list: Information[] = [];

    const dto = this.data();

    if (dto) {
      list.push({ name: 'Department Code', value: dto.code });
      list.push({ name: 'Department Name', value: dto.name });

      if (dto.description) {
        list.push({ name: 'Description', value: dto.description });
      }

      const hod = dto.hod;

      if (hod) {
        list.push({ name: 'HOD Code', value: hod.code });
        list.push({ name: 'HOD Name', value: hod.name });
        list.push({ name: 'HOD Phone', value: hod.phone });
        list.push({ name: 'HOD Position', value: hod.positionName });
      }
    }

    return list;
  });

  data = signal<any>(undefined);
  title = computed(() =>
    this.data()
      ? `${this.data().code} - ${this.data().name}`
      : 'Department Details'
  );

  constructor(service: DepartmentService) {
    effect(() => {
      if (this.code()) {
        service.findById(this.code()).subscribe((result) => {
          if (result.success) {
            this.data.set(result.payload);
          }
        });
      }
    });
  }
}
