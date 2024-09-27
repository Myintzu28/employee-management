import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PaginationComponent } from './pagination/pagination.component';
import { FormGroupComponent } from './form-group/form-group.component';
import { InputGroupComponent } from './input-group/input-group.component';
import { FormGroupCheckComponent } from './form-group-check/form-group-check.component';
import { PageTitleComponent } from './page-title/page-title.component';
import { ListViewComponent } from './list-view/list-view.component';
import { CardComponent } from './card/card.component';
import { NoDataComponent } from './no-data/no-data.component';

@NgModule({
  declarations: [
    PageTitleComponent,
    PaginationComponent,
    FormGroupComponent,
    InputGroupComponent,
    FormGroupCheckComponent,
    ListViewComponent,
    CardComponent,
    NoDataComponent,
  ],
  imports: [CommonModule],
  exports: [
    PageTitleComponent,
    PaginationComponent,
    FormGroupComponent,
    InputGroupComponent,
    FormGroupCheckComponent,
    ListViewComponent,
    CardComponent,
    NoDataComponent,
  ],
})
export class WidgetsModule {}
