package id.co.chubb.fileprocess.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
    ReportingEodProperties reportingEodProperties = new ReportingEodProperties();

    public ReportingEodProperties getReportingEodProperties() {
        return reportingEodProperties;
    }

    public static class ReportingEodProperties {
        private String directoryOutOnline;
        private String directoryOutOfflineInv;
        private String directoryOutNonsharing;
        private String directoryOutSharing;
        private String filenameReportOnline;
        private String filenameReportOfflineNonsharing;
        private String filenameReportOfflineSharing;
        private String filenameReportOfflineTolakanNonsharing;
        private String filenameReportOfflineTolakanSharing;
        private String filenameReportOfflineInv;
        private String filenameReportValidMte;
        private String filenameReportMte;
        private Boolean generateReportMteNotYet;
        private String errorListMte;
        private String jobReportInvalidReport;
        private String jobReportValidMte;
        private String jobReportMteNotYet;
        private String jobReportOnline;

        private String jobReportValidNonSharing;
        private String jobReportValidSharing;
        private Boolean transferFiles = Boolean.FALSE;
        private Boolean useConfigFromFiles = Boolean.FALSE;
        private Long mteNotYetMinusDays;
        private String dirConfigFtp;
        private String filenameReportSuspendTrfInv;
        private String filenameReportSuspendValidMte;
        private String filenameReportSuspendOnline;

        private String nonSharingFormatDateFilename;
        private String sharingFormatDateFilename;

        private String onlineFormatDateFilename;
        private boolean generateOnlineWithoutMapping = false;

        public boolean getGenerateOnlineWithoutMapping() {
            return generateOnlineWithoutMapping;
        }

        public void setGenerateOnlineWithoutMapping(boolean generateOnlineWithoutMapping) {
            this.generateOnlineWithoutMapping = generateOnlineWithoutMapping;
        }

        public String getOnlineFormatDateFilename() {
            return onlineFormatDateFilename;
        }

        public void setOnlineFormatDateFilename(String onlineFormatDateFilename) {
            this.onlineFormatDateFilename = onlineFormatDateFilename;
        }

        public String getSharingFormatDateFilename() {
            return sharingFormatDateFilename;
        }

        public void setSharingFormatDateFilename(String sharingFormatDateFilename) {
            this.sharingFormatDateFilename = sharingFormatDateFilename;
        }

        public String getNonSharingFormatDateFilename() {
            return nonSharingFormatDateFilename;
        }

        public void setNonSharingFormatDateFilename(String nonSharingFormatDateFilename) {
            this.nonSharingFormatDateFilename = nonSharingFormatDateFilename;
        }

        public String getFilenameReportSuspendOnline() {
            return filenameReportSuspendOnline;
        }

        public void setFilenameReportSuspendOnline(String filenameReportSuspendOnline) {
            this.filenameReportSuspendOnline = filenameReportSuspendOnline;
        }

        public String getJobReportOnline() {
            return jobReportOnline;
        }

        public void setJobReportOnline(String jobReportOnline) {
            this.jobReportOnline = jobReportOnline;
        }

        public String getJobReportValidSharing() {
            return jobReportValidSharing;
        }

        public void setJobReportValidSharing(String jobReportValidSharing) {
            this.jobReportValidSharing = jobReportValidSharing;
        }

        public void setFilenameReportOfflineTolakanNonsharing(String filenameReportOfflineTolakanNonsharing) {
            this.filenameReportOfflineTolakanNonsharing = filenameReportOfflineTolakanNonsharing;
        }

        public String getDirectoryOutSharing() {
            return directoryOutSharing;
        }

        public String getJobReportValidNonSharing() {
            return jobReportValidNonSharing;
        }

        public void setJobReportValidNonSharing(String jobReportValidNonSharing) {
            this.jobReportValidNonSharing = jobReportValidNonSharing;
        }

        public String getFilenameReportOfflineTolakanNonsharing() {
            return filenameReportOfflineTolakanNonsharing;
        }

        public String getFilenameReportOfflineTolakanSharing() {
            return filenameReportOfflineTolakanSharing;
        }

        public void setFilenameReportOfflineTolakanSharing(String filenameReportOfflineTolakanSharing) {
            this.filenameReportOfflineTolakanSharing = filenameReportOfflineTolakanSharing;
        }

        public void setDirectoryOutSharing(String directoryOutSharing) {
            this.directoryOutSharing = directoryOutSharing;
        }

        public String getDirectoryOutNonsharing() {
            return directoryOutNonsharing;
        }

        public void setDirectoryOutNonsharing(String directoryOutNonsharing) {
            this.directoryOutNonsharing = directoryOutNonsharing;
        }

        public String getFilenameReportOfflineNonsharing() {
            return filenameReportOfflineNonsharing;
        }

        public void setFilenameReportOfflineNonsharing(String filenameReportOfflineNonsharing) {
            this.filenameReportOfflineNonsharing = filenameReportOfflineNonsharing;
        }

        public String getFilenameReportOfflineSharing() {
            return filenameReportOfflineSharing;
        }

        public void setFilenameReportOfflineSharing(String filenameReportOfflineSharing) {
            this.filenameReportOfflineSharing = filenameReportOfflineSharing;
        }

        public String getDirectoryOutOnline() {
            return directoryOutOnline;
        }

        public void setDirectoryOutOnline(String directoryOutOnline) {
            this.directoryOutOnline = directoryOutOnline;
        }

        public String getDirectoryOutOfflineInv() {
            return directoryOutOfflineInv;
        }

        public void setDirectoryOutOfflineInv(String directoryOutOfflineInv) {
            this.directoryOutOfflineInv = directoryOutOfflineInv;
        }

        public String getFilenameReportOnline() {
            return filenameReportOnline;
        }

        public void setFilenameReportOnline(String filenameReportOnline) {
            this.filenameReportOnline = filenameReportOnline;
        }

        public String getFilenameReportOfflineInv() {
            return filenameReportOfflineInv;
        }

        public void setFilenameReportOfflineInv(String filenameReportOfflineInv) {
            this.filenameReportOfflineInv = filenameReportOfflineInv;
        }

        public String getFilenameReportValidMte() {
            return filenameReportValidMte;
        }

        public void setFilenameReportValidMte(String filenameReportValidMte) {
            this.filenameReportValidMte = filenameReportValidMte;
        }

        public String getFilenameReportMte() {
            return filenameReportMte;
        }

        public void setFilenameReportMte(String filenameReportMte) {
            this.filenameReportMte = filenameReportMte;
        }

        public Boolean getGenerateReportMteNotYet() {
            return generateReportMteNotYet;
        }

        public void setGenerateReportMteNotYet(Boolean generateReportMteNotYet) {
            this.generateReportMteNotYet = generateReportMteNotYet;
        }

        public String getErrorListMte() {
            return errorListMte;
        }

        public void setErrorListMte(String errorListMte) {
            this.errorListMte = errorListMte;
        }

        public String getJobReportInvalidReport() {
            return jobReportInvalidReport;
        }

        public void setJobReportInvalidReport(String jobReportInvalidReport) {
            this.jobReportInvalidReport = jobReportInvalidReport;
        }

        public String getJobReportValidMte() {
            return jobReportValidMte;
        }

        public void setJobReportValidMte(String jobReportValidMte) {
            this.jobReportValidMte = jobReportValidMte;
        }

        public String getJobReportMteNotYet() {
            return jobReportMteNotYet;
        }

        public void setJobReportMteNotYet(String jobReportMteNotYet) {
            this.jobReportMteNotYet = jobReportMteNotYet;
        }

        public Boolean getTransferFiles() {
            return transferFiles;
        }

        public void setTransferFiles(Boolean transferFiles) {
            this.transferFiles = transferFiles;
        }

        public Boolean getUseConfigFromFiles() {
            return useConfigFromFiles;
        }

        public void setUseConfigFromFiles(Boolean useConfigFromFiles) {
            this.useConfigFromFiles = useConfigFromFiles;
        }

        public Long getMteNotYetMinusDays() {
            return mteNotYetMinusDays;
        }

        public void setMteNotYetMinusDays(Long mteNotYetMinusDays) {
            this.mteNotYetMinusDays = mteNotYetMinusDays;
        }

        public String getDirConfigFtp() {
            return dirConfigFtp;
        }

        public void setDirConfigFtp(String dirConfigFtp) {
            this.dirConfigFtp = dirConfigFtp;
        }

        public String getFilenameReportSuspendTrfInv() {
            return filenameReportSuspendTrfInv;
        }

        public void setFilenameReportSuspendTrfInv(String filenameReportSuspendTrfInv) {
            this.filenameReportSuspendTrfInv = filenameReportSuspendTrfInv;
        }

        public String getFilenameReportSuspendValidMte() {
            return filenameReportSuspendValidMte;
        }

        public void setFilenameReportSuspendValidMte(String filenameReportSuspendValidMte) {
            this.filenameReportSuspendValidMte = filenameReportSuspendValidMte;
        }
    }
}
