import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShortestPathComponent } from './shortest-path.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { ShortestPathService } from './shortest-path.service';
import { HttpClientModule } from '@angular/common/http';

describe('ShortestPathComponent', () => {
  let component: ShortestPathComponent;
  let fixture: ComponentFixture<ShortestPathComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShortestPathComponent ],
      imports: [
        ReactiveFormsModule,
        FormsModule,
        HttpClientModule
      ],
      providers: [ShortestPathService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShortestPathComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it(`should have path empty on load`, () => {
    const fixture = TestBed.createComponent(ShortestPathComponent);
    const app = fixture.componentInstance;
    expect(app.path).toEqual('');
  });

  it(`should have empty planet list on startup`, () => {
    const fixture = TestBed.createComponent(ShortestPathComponent);
    const app = fixture.componentInstance;
    expect(app.planets).toEqual([]);
  });

  it(`should have form as invalid on startup`, () => {
    const fixture = TestBed.createComponent(ShortestPathComponent);
    const app = fixture.componentInstance;
    expect(app.form.valid).toBeFalse();
  });

});
