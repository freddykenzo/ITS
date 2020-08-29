import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ShortestPathComponent } from './shortest-path/shortest-path.component';


const routes: Routes = [
  { path : '', component: ShortestPathComponent},
  { path : 'shortest-path', component: ShortestPathComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
