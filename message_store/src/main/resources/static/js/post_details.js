let likeSection = document.getElementById('like-section');
if (likeSection) {
    likeSection.addEventListener("click", reviewPostDetails);
}
let dislikeSection = document.getElementById('dislike-section');
if (dislikeSection) {
    dislikeSection.addEventListener("click", reviewPostDetails);
}

function reviewPostDetails() {
    console.log('reviewPostDetails');
    console.log(this);
}
