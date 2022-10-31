import { Component, OnInit } from '@angular/core';
import { Present } from 'src/app/models/present';
import { PresentService } from 'src/app/services/present.service';

@Component({
  selector: 'app-presents',
  templateUrl: './presents.component.html',
  styleUrls: ['./presents.component.scss']
})
export class PresentsComponent implements OnInit {

  presents?: Present[];

  constructor(private presentService: PresentService) { }

  ngOnInit(): void {
    this.presentService.getPresentList().subscribe(
      value => {
        this.presents = value;
      }
    )
  }

}
