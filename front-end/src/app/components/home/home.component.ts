import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Live } from 'src/app/models/Live';
import { LiveService } from 'src/app/services/live.service';
import { LiveFormDialogComponent } from '../live-form-dialog/live-form-dialog.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  livesPrevious: Live[];
  livesPreviousReady: boolean = false;
  url: string = '';
  urlSafe: SafeResourceUrl;

  constructor(
    private rest: LiveService,
    private sanitizer: DomSanitizer,
    private dialog: MatDialog) { }

  ngOnInit(): void {
    this.getLives();
  }

  getLives(){
    this.rest.getLivesWithFlag().subscribe(data => {
      this.livesPrevious = data.content;
      this.livesPrevious.forEach(live => {
        live.urlSafe = this.sanitizer.bypassSecurityTrustResourceUrl(live.liveLink);
      });
      this.livesPreviousReady = true;
    });
    
  }

  addLive(): void {
    const dialogRef = this.dialog.open(LiveFormDialogComponent, {
      // maxHeight: '95vh',
      minWidth: '400px',
      // width: '25vw',
    });

    dialogRef.afterClosed().subscribe(result => {
      // window.location.reload();
    });
  }
  

}
