function sendUrl(url) {
    const res = await fetch('/crawl', {
        method: 'post'
    });
    const json = res.json();
    console.log(json);
}