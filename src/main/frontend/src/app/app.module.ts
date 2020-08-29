import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ShortestPathComponent } from './shortest-path/shortest-path.component';
import { HttpClientModule } from '@angular/common/http';
import { ShortestPathService } from './shortest-path/shortest-path.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    ShortestPathComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  providers: [ShortestPathService],
  bootstrap: [AppComponent]
})
export class AppModule { }
