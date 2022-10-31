import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DrawComponent } from './components/draw/draw.component';
import { PresentsComponent } from './components/presents/presents.component';
import { AuthGuard } from './guard/auth.guard';

const routes: Routes = [
  {path: '', component: DrawComponent, canActivate: [AuthGuard]},
  {path: 'presents', component: PresentsComponent, canActivate: [AuthGuard]},
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
