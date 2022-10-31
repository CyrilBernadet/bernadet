import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PresentService } from 'src/app/services/present.service';

@Component({
  selector: 'app-draw',
  templateUrl: './draw.component.html',
  styleUrls: ['./draw.component.scss'],
  animations: [
    trigger('draw', [
      state('inside', style(
        { transform: 'translateY(500%)' }
      )),
      state('outside', style(
        { transform: 'translateY(200%)' }
      )),
      transition('inside => outside', [
        animate('3s 0s ease-in-out')
      ])
    ])
  ]
})
export class DrawComponent implements OnInit {

  isLoading = true;
  isInside = true;
  isNavigating = false;

  drawnName?: string = 'Personne';

  constructor(private presentService: PresentService, private router: Router) { }

  ngOnInit(): void {
    this.presentService.hasDrawn().subscribe(value => {
      if (value) {
        this.router.navigate(['/presents']);
      } else {
        this.isLoading = false;
      }
    });
  }

  toggle() {
    if (!this.isNavigating) {
      if (this.isInside) {
        this.presentService.draw().subscribe(
          {
            next: (value) => {
              this.drawnName = value.firstName;
              this.isInside = !this.isInside;
            }
          });
      } else {
        this.isNavigating = true;
        this.isInside = !this.isInside;
        setTimeout(() => {
          this.router.navigate(['/presents']);
        }, 1000);
      }
    }
  }
}
