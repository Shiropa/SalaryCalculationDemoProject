import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {DefaultLayoutComponent} from './container/default-layout';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {HeaderComponent} from './container/header/header.component';
import {FooterComponent} from './container/footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    DefaultLayoutComponent,
    HeaderComponent,
    FooterComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
