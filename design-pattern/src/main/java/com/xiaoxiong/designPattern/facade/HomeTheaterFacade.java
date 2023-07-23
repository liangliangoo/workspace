package com.xiaoxiong.designPattern.facade;

/**
 * @author liangliang
 * @date 2021/2/15 20:22
 * @e-mail 1640432919@qq.com
 */
public class HomeTheaterFacade {

    private Popcorn popcorn;
    private DVDPlayer dvdPlayer;
    private Projector projector;
    private Screen screen;
    private Stereo stereo;
    private TheaterLight theaterLight;

    public HomeTheaterFacade() {
        this.popcorn = Popcorn.getInstance();
        this.dvdPlayer = DVDPlayer.getInstance();
        this.projector = Projector.getInstance();
        this.screen = Screen.getInstance();
        this.stereo = Stereo.getInstance();
        this.theaterLight = TheaterLight.getInstance();
    }

    public void ready() {
        popcorn.on();
        popcorn.pop();
        screen.down();
        projector.on();
        stereo.on();
        dvdPlayer.on();
        theaterLight.dim();
    }

    public void play() {
        dvdPlayer.play();
    }

    public void pause() {
        dvdPlayer.pause();
    }

    public void end() {
        theaterLight.bright();
        popcorn.off();
        screen.up();
        projector.off();
        stereo.off();
        dvdPlayer.off();
    }

}
